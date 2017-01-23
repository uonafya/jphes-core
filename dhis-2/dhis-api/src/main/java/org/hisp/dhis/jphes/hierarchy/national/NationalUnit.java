package org.hisp.dhis.jphes.hierarchy.national;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.common.MergeMode;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.user.UserGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bangadennis on 05/01/17.
 */

@JacksonXmlRootElement( localName = "nationalUnit", namespace = DxfNamespaces.DXF_2_0 )
public class NationalUnit extends BaseIdentifiableObject
{
    private String description;

    private String shortName;

    private UserGroup userGroup;

    private DataElementCategory mechanismCategory;

    //Mechanism CategoryCombo -Holds the mechanismCategory
    private DataElementCategoryCombo mechanismCombo;

    //Donor CategoryOptionGroupSet
    private CategoryOptionGroupSet categoryOptionGroupSetAgency;

    //Donor CategoryOptionGroupSet
    private CategoryOptionGroupSet categoryOptionGroupSet;

    private Set<Program> programs = new HashSet<>();

    private Set<DonorUnit> donorUnits = new HashSet<>();

    private Boolean enabled;


    public NationalUnit(){

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
    public DataElementCategory getMechanismCategory()
    {
        return mechanismCategory;
    }

    public void setMechanismCategory( DataElementCategory mechanismCategory )
    {
        this.mechanismCategory = mechanismCategory;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public DataElementCategoryCombo getMechanismCombo()
    {
        return mechanismCombo;
    }

    public void setMechanismCombo( DataElementCategoryCombo mechanismCombo )
    {
        this.mechanismCombo = mechanismCombo;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public CategoryOptionGroupSet getCategoryOptionGroupSetAgency()
    {
        return categoryOptionGroupSetAgency;
    }

    public void setCategoryOptionGroupSetAgency( CategoryOptionGroupSet categoryOptionGroupSetAgency )
    {
        this.categoryOptionGroupSetAgency = categoryOptionGroupSetAgency;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public CategoryOptionGroupSet getCategoryOptionGroupSet()
    {
        return categoryOptionGroupSet;
    }

    public void setCategoryOptionGroupSet( CategoryOptionGroupSet categoryOptionGroupSet )
    {
        this.categoryOptionGroupSet = categoryOptionGroupSet;
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
    @JacksonXmlElementWrapper( localName = "donorUnits", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "donorUnit", namespace = DxfNamespaces.DXF_2_0 )
    public Set<DonorUnit> getDonorUnits()
    {
        return donorUnits;
    }

    public void setDonorUnits( Set<DonorUnit> donorUnits )
    {
        this.donorUnits = donorUnits;
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

    @Override
    public void mergeWith( IdentifiableObject other, MergeMode mergeMode )
    {

        super.mergeWith( other, mergeMode );

        if ( other.getClass().isInstance( this ) )
        {

            NationalUnit nationalUnit = (NationalUnit) other;

            if ( mergeMode.isReplace() )
            {
                name = nationalUnit.getName();
                code = nationalUnit.getCode();
                shortName = nationalUnit.getShortName();
                description = nationalUnit.getDescription();
                programs = nationalUnit.getPrograms();
                userGroup = nationalUnit.getUserGroup();
                enabled = nationalUnit.getEnabled();
            }

            if ( mergeMode.isMerge() )
            {
                name = nationalUnit.getName()==null ? name : nationalUnit.getName();
                code = nationalUnit.getCode() == null ? code : nationalUnit.getCode();
                shortName = nationalUnit.getShortName() == null ? shortName : nationalUnit.getShortName();
                description = nationalUnit.getDescription() == null ? description : nationalUnit.getDescription();
                programs = nationalUnit.getPrograms() == null ? programs : nationalUnit.getPrograms();
                userGroup = nationalUnit.getUserGroup() == null ? userGroup : nationalUnit.getUserGroup();
                enabled = nationalUnit.getEnabled() == null ? enabled : nationalUnit.getEnabled();
            }
        }
    }




}
