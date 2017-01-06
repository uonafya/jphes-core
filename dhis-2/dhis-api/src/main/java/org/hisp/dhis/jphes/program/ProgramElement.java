package org.hisp.dhis.jphes.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.common.base.Objects;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.common.MergeMode;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;

/**
 * Created by xenial on 1/6/17.
 */
public class ProgramElement extends BaseIdentifiableObject {
    /**
     * Program, never null.
     */
    private Program program;

    /**
     * Data element, never null.
     */
    private DataElement dataElement;

    /**
     * Category combination, potentially null.
     */
    private DataElementCategoryCombo categoryCombo;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public ProgramElement()
    {
        setAutoFields();
    }

    public ProgramElement( Program program, DataElement dataElement )
    {
        setAutoFields();
        this.dataElement = dataElement;
        this.program = program;
    }

    public ProgramElement( Program program, DataElement dataElement, DataElementCategoryCombo categoryCombo )
    {
        setAutoFields();
        this.program = program;
        this.dataElement = dataElement;
        this.categoryCombo = categoryCombo;
    }

    // -------------------------------------------------------------------------
    // Logic
    // -------------------------------------------------------------------------

    /**
     * Returns the category combination of this data set element, if null,
     * returns the category combination of the data element of this data set
     * element.
     */
    public DataElementCategoryCombo getResolvedCategoryCombo()
    {
        return hasCategoryCombo() ? getCategoryCombo() : dataElement.getDataElementCategoryCombo();
    }

    public boolean hasCategoryCombo()
    {
        return categoryCombo != null;
    }

    // -------------------------------------------------------------------------
    // Hash code and equals
    // -------------------------------------------------------------------------

    @Override
    public int hashCode()
    {
        return Objects.hashCode( super.hashCode(), program, dataElement );
    }

    @Override
    public boolean equals( Object object )
    {
        if ( this == object )
        {
            return true;
        }

        if ( object == null )
        {
            return false;
        }

        if ( !getClass().isAssignableFrom( object.getClass() ) )
        {
            return false;
        }

        ProgramElement other = (ProgramElement) object;

        return objectEquals( other );
    }

    public boolean objectEquals(ProgramElement other )
    {
        return program.equals( other.getProgram() ) && dataElement.equals( other.getDataElement() );
    }

    @Override
    public String toString()
    {
        return "{" +
                "\"class\":\"" + getClass() + "\", " +
                "\"id\":\"" + getId() + "\", " +
                "\"uid\":\"" + getUid() + "\", " +
                "\"code\":\"" + getCode() + "\", " +
                "\"name\":\"" + getName() + "\", " +
                "\"created\":\"" + getCreated() + "\", " +
                "\"lastUpdated\":\"" + getLastUpdated() + "\", " +
                "\"program\":\"" + program + "\", " +
                "\"dataElement\":\"" + dataElement + "\" " +
                "\"categoryCombo\":\"" + categoryCombo + "\" " +
                "}";
    }

    // -------------------------------------------------------------------------
    // Get and set methods
    // -------------------------------------------------------------------------

    @JsonProperty
    @JsonSerialize( as = BaseIdentifiableObject.class )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Program getProgram()
    {
        return program;
    }

    public void setProgram( Program program )
    {
        this.program = program;
    }

    @JsonProperty
    @JsonSerialize( as = BaseIdentifiableObject.class )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public DataElement getDataElement()
    {
        return dataElement;
    }

    public void setDataElement( DataElement dataElement )
    {
        this.dataElement = dataElement;
    }

    /**
     * Category combination of this program element. Can be null, use
     * {@link #getResolvedCategoryCombo} to get fall back to category
     * combination of data element.
     */
    @JsonProperty
    @JsonSerialize( as = BaseIdentifiableObject.class )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public DataElementCategoryCombo getCategoryCombo()
    {
        return categoryCombo;
    }

    public void setCategoryCombo( DataElementCategoryCombo categoryCombo )
    {
        this.categoryCombo = categoryCombo;
    }

    @Override
    public void mergeWith(IdentifiableObject other, MergeMode mergeMode )
    {
        super.mergeWith( other, mergeMode );

        if ( other.getClass().isInstance( this ) )
        {
            ProgramElement programElement = (ProgramElement) other;

            if ( mergeMode.isReplace() )
            {
                program = programElement.getProgram();
                dataElement = programElement.getDataElement();
                categoryCombo = programElement.getCategoryCombo();
            }
            else if ( mergeMode.isMerge() )
            {
                program = programElement.getProgram() == null ? program : programElement.getProgram();
                dataElement = programElement.getDataElement() == null ? dataElement : programElement.getDataElement();
                categoryCombo = programElement.getCategoryCombo() == null ? categoryCombo : programElement.getCategoryCombo();
            }
        }
    }
}
