package org.hisp.dhis.jphes.hierarchy.agency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.user.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bangadennis on 11/01/17.
 */

@JacksonXmlRootElement(localName = "agencyUnit", namespace = DxfNamespaces.DXF_2_0)
public class AgencyUnit extends BaseIdentifiableObject
{
    private String description;

    private String shortName;

    private DonorUnit donorUnit;

    private UserGroup userGroup;

    private CategoryOptionGroup categoryOptionGroup;

    private Set<Program> programs = new HashSet<>();

    private Set<MechanismUnit> mechanismUnits = new HashSet<>( );

    private Boolean enabled;


    public AgencyUnit(){

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
    public DonorUnit getDonorUnit()
    {
        return donorUnit;
    }

    public void setDonorUnit( DonorUnit donorUnit )
    {
        this.donorUnit = donorUnit;
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
    @JacksonXmlElementWrapper( localName = "mechanismUnits", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "mechanismUnit", namespace = DxfNamespaces.DXF_2_0 )
    public Set<MechanismUnit> getMechanismUnits()
    {
        return mechanismUnits;
    }

    public void setMechanismUnits( Set<MechanismUnit> mechanismUnits )
    {
        this.mechanismUnits = mechanismUnits;
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
