package org.hisp.dhis.dxf2.metadata.objectbundle.hooks;

/*
 * Copyright (c) 2004-2016, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.hibernate.Session;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.dataelement.DataElementOperand;
import org.hisp.dhis.dataset.DataSet;
import org.hisp.dhis.dataset.DataSetElement;
import org.hisp.dhis.dxf2.metadata.objectbundle.ObjectBundle;
import org.hisp.dhis.period.PeriodType;

import java.util.Map;
import java.util.Set;

/**
 * @author Morten Olav Hansen <mortenoh@gmail.com>
 */
public class DataSetObjectBundleHook extends AbstractObjectBundleHook
{
    @Override
    public void preCreate( IdentifiableObject object, ObjectBundle bundle )
    {
        if ( !DataSet.class.isInstance( object ) ) return;
        DataSet dataSet = (DataSet) object;

        Session session = sessionFactory.getCurrentSession();

        for ( DataSetElement dataSetElement : dataSet.getDataSetElements() )
        {
            preheatService.connectReferences( dataSetElement, bundle.getPreheat(), bundle.getPreheatIdentifier() );
        }

        for ( DataElementOperand dataElementOperand : dataSet.getCompulsoryDataElementOperands() )
        {
            preheatService.connectReferences( dataElementOperand, bundle.getPreheat(), bundle.getPreheatIdentifier() );
            session.save( dataElementOperand );
        }

        if ( dataSet.getPeriodType() != null )
        {
            PeriodType periodType = bundle.getPreheat().getPeriodTypeMap().get( dataSet.getPeriodType().getName() );
            dataSet.setPeriodType( periodType );
        }
    }

    @Override
    public void preUpdate( IdentifiableObject object, IdentifiableObject persistedObject, ObjectBundle bundle )
    {
        if ( !DataSet.class.isInstance( object ) ) return;
        DataSet dataSet = (DataSet) object;
        DataSet persistedDataSet = (DataSet) persistedObject;

        dataSet.getDataSetElements().clear();
        persistedDataSet.getDataSetElements().clear();

        dataSet.getCompulsoryDataElementOperands().clear();
        persistedDataSet.getCompulsoryDataElementOperands().clear();

        if ( dataSet.getPeriodType() != null )
        {
            PeriodType periodType = bundle.getPreheat().getPeriodTypeMap().get( dataSet.getPeriodType().getName() );
            dataSet.setPeriodType( periodType );
        }

        sessionFactory.getCurrentSession().flush();
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public void postUpdate( IdentifiableObject persistedObject, ObjectBundle bundle )
    {
        if ( !DataSet.class.isInstance( persistedObject ) ) return;
        if ( !bundle.getObjectReferences().containsKey( DataSet.class ) ) return;
        DataSet dataSet = (DataSet) persistedObject;

        Map<String, Object> references = bundle.getObjectReferences( DataSet.class ).get( dataSet.getUid() );
        if ( references == null ) return;

        Set<DataSetElement> dataSetElements = (Set<DataSetElement>) references.get( "dataSetElements" );

        if ( dataSetElements != null && !dataSetElements.isEmpty() )
        {
            for ( DataSetElement dataSetElement : dataSetElements )
            {
                preheatService.connectReferences( dataSetElement, bundle.getPreheat(), bundle.getPreheatIdentifier() );
                dataSet.getDataSetElements().add( dataSetElement );
            }
        }

        Set<DataElementOperand> dataElementOperands = (Set<DataElementOperand>) references.get( "compulsoryDataElementOperands" );

        if ( dataElementOperands != null && !dataElementOperands.isEmpty() )
        {
            for ( DataElementOperand dataElementOperand : dataElementOperands )
            {
                preheatService.connectReferences( dataElementOperand, bundle.getPreheat(), bundle.getPreheatIdentifier() );
                sessionFactory.getCurrentSession().save( dataElementOperand );
                dataSet.getCompulsoryDataElementOperands().add( dataElementOperand );
            }
        }

        sessionFactory.getCurrentSession().update( dataSet );
    }
}
