package org.hisp.dhis.dxf2.metadata;

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

import org.hisp.dhis.common.MergeMode;
import org.hisp.dhis.schema.Property;
import org.hisp.dhis.schema.PropertyType;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaService;
import org.hisp.dhis.system.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author Morten Olav Hansen <mortenoh@gmail.com>
 */
public class DefaultMergeService implements MergeService
{
    @Autowired
    private SchemaService schemaService;

    @Override
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public <T> void merge( T source, T target, MergeMode mergeMode )
    {
        if ( source == null || target == null )
        {
            return;
        }

        Schema schema = schemaService.getDynamicSchema( source.getClass() );

        for ( Property property : schema.getProperties() )
        {
            // passwords should only be merged manually
            if ( property.is( PropertyType.PASSWORD ) )
            {
                continue;
            }

            if ( property.isCollection() )
            {
                Collection sourceObject = ReflectionUtils.invokeMethod( source, property.getGetterMethod() );
                Collection targetObject = ReflectionUtils.invokeMethod( target, property.getGetterMethod() );

                if ( sourceObject == null )
                {
                    continue;
                }

                if ( targetObject == null )
                {
                    targetObject = ReflectionUtils.newCollectionInstance( property.getKlass() );
                }

                targetObject.clear();
                targetObject.addAll( sourceObject );

                ReflectionUtils.invokeMethod( target, property.getSetterMethod(), targetObject );
            }
            else
            {
                Object sourceObject = ReflectionUtils.invokeMethod( source, property.getGetterMethod() );

                if ( mergeMode.isReplace() )
                {
                    ReflectionUtils.invokeMethod( target, property.getSetterMethod(), sourceObject );
                }
                else if ( mergeMode.isMerge() && sourceObject != null )
                {
                    ReflectionUtils.invokeMethod( target, property.getSetterMethod(), sourceObject );
                }
            }
        }
    }
}
