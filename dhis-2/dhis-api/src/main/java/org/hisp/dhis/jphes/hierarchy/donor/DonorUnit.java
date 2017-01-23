package org.hisp.dhis.jphes.hierarchy.donor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.user.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bangadennis on 11/01/17.
 */
@JacksonXmlRootElement(localName = "donorUnit", namespace = DxfNamespaces.DXF_2_0)
public class DonorUnit extends BaseIdentifiableObject
{
    private String description;

    private String shortName;

    private NationalUnit nationalUnit;

    private UserGroup userGroup;

    //DonorOptionGroup- Hold MechanismsCategoryOptions
    private CategoryOptionGroup categoryOptionGroup;

    private Set<Program> programs = new HashSet<>();

    private Set<AgencyUnit> agencyUnits = new HashSet<>( );

    private Boolean enabled;


    public DonorUnit(){

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
    public NationalUnit getNationalUnit()
    {
        return nationalUnit;
    }

    public void setNationalUnit( NationalUnit nationalUnit )
    {
        this.nationalUnit = nationalUnit;
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
    public CategoryOptionGroup getCategoryOptionGroup()
    {
        return categoryOptionGroup;
    }

    public void setCategoryOptionGroup( CategoryOptionGroup categoryOptionGroup )
    {
        this.categoryOptionGroup = categoryOptionGroup;
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
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JacksonXmlElementWrapper( localName = "agencyUnits", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "agencyUnit", namespace = DxfNamespaces.DXF_2_0 )
    public Set<AgencyUnit> getAgencyUnits()
    {
        return agencyUnits;
    }

    public void setAgencyUnits( Set<AgencyUnit> agencyUnits )
    {
        this.agencyUnits = agencyUnits;
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
