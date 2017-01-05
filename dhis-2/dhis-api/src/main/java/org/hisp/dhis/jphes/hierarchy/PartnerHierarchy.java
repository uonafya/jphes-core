package org.hisp.dhis.jphes.hierarchy;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;

/**
 * Created by afya on 30/12/16.
 */

@JacksonXmlRootElement( localName = "PartnerHierarchy", namespace = DxfNamespaces.DXF_2_0 )
public class PartnerHierarchy extends BaseIdentifiableObject
{
    //Inherits all attributes

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    public PartnerHierarchy(){

    }
}
