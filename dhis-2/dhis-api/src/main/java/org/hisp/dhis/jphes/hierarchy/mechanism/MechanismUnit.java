package org.hisp.dhis.jphes.hierarchy.mechanism;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.dataelement.DataElementCategoryOption;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.user.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bangadennis on 11/01/17.
 */
public class MechanismUnit extends BaseIdentifiableObject
{
    private String description;

    private String shortName;

    private String partner;

    private AgencyUnit agencyUnit;

    private UserGroup userGroup;

    private DataElementCategoryOption categoryOption;

    private Set<Program> programs = new HashSet<>();

    private Boolean enabled;


    public MechanismUnit()
    {

    }

    //logic

    public void addProgram( Program program )
    {
        programs.add( program );
    }

    public boolean removeProgram( Program program )
    {
        return programs.remove( program );
    }

    public void removeAllPrograms()
    {
        programs.clear();
    }

    // Getters and Setters

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getShortName()
    {
        return shortName;
    }

    public void setShortName( String shortName )
    {
        this.shortName = shortName;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getPartner()
    {
        return partner;
    }

    public void setPartner( String partner )
    {
        this.partner = partner;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public AgencyUnit getAgencyUnit()
    {
        return agencyUnit;
    }

    public void setAgencyUnit( AgencyUnit agencyUnit )
    {
        this.agencyUnit = agencyUnit;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public UserGroup getUserGroup()
    {
        return userGroup;
    }

    public void setUserGroup( UserGroup userGroup )
    {
        this.userGroup = userGroup;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public DataElementCategoryOption getCategoryOption()
    {
        return categoryOption;
    }

    public void setCategoryOption( DataElementCategoryOption categoryOption )
    {
        this.categoryOption = categoryOption;
    }

    @JsonProperty
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JacksonXmlElementWrapper( localName = "programs", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "program", namespace = DxfNamespaces.DXF_2_0 )
    public Set<Program> getPrograms()
    {
        return programs;
    }

    public void setPrograms( Set<Program> programs )
    {
        this.programs = programs;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled( Boolean enabled )
    {
        this.enabled = enabled;
    }
}
