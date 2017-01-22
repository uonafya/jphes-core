package org.hisp.dhis.jphes.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.collect.ImmutableSet;
import org.hisp.dhis.common.BaseDimensionalItemObject;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.VersionedObject;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.schema.PropertyType;
import org.hisp.dhis.schema.annotation.Property;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xenial on 1/4/17.
 */
@JacksonXmlRootElement( localName = "program", namespace = DxfNamespaces.DXF_2_0 )
public class Program extends BaseDimensionalItemObject implements VersionedObject {

    /**
     * formName is what will be displayed to the user
     * useful where name is too long
     */
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String formName) {
        this.displayName = displayName;
    }

    /**
     * All DataElements associated with this DataSet.
     */
    private Set<DataElement> programElements = new HashSet<>();

    /**
     * Indicators associated with this data set. Indicators are used for view
     * and output purposes, such as calculated fields in forms and reports.
     */
    private Set<Indicator> indicators = new HashSet<>();

    /**
     * The start date.
     */
    private Date startDate;

    /**
     * The end date.
     */
    private Date endDate;


    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public  Program(){

    }

    public Program(String name){
        this.name = name;
    }

    public Program(String name, String displayName){
        this(name);
    }

    public Program(String name, String displayName, String code){
        this(name, displayName);
        this.code = code;
    }

//---------------------------------------------------------------------------------------------------------
//    -----------------------------------------------------------------------------------------------------


//    public void addIndicator( Indicator indicator )
//    {
//        indicators.add( indicator );
//        indicator.getPrograms().add( this );
//    }
//
//    public boolean removeIndicator( Indicator indicator )
//    {
//        indicators.remove( indicator );
//        return indicator.getPrograms().remove( this );
//    }

    @JsonProperty
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JacksonXmlElementWrapper( localName = "programElements", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "programElement", namespace = DxfNamespaces.DXF_2_0 )
    public Set<DataElement> getProgramElements()
    {
        return programElements;
    }

    public void setProgramElements( Set<DataElement> programElements )
    {
        this.programElements = programElements;
    }

    @JsonProperty
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JacksonXmlElementWrapper( localName = "indicators", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "indicator", namespace = DxfNamespaces.DXF_2_0 )
    public Set<Indicator> getIndicators()
    {
        return indicators;
    }

    public void setIndicators( Set<Indicator> indicators )
    {
        this.indicators = indicators;
    }


    @JsonProperty
    @Property( value = PropertyType.DATE, required = Property.Value.FALSE )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate( Date startDate )
    {
        this.startDate = startDate;
    }

    @JsonProperty
    @Property( value = PropertyType.DATE, required = Property.Value.FALSE )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate( Date endDate )
    {
        this.endDate = endDate;
    }

    /**
     * Returns the current version.
     */
    @Override
    public int getVersion() {
        return 0;
    }

    /**
     * Sets the version.
     *
     * @param version
     */
    @Override
    public void setVersion(int version) {

    }

    /**
     * Increases the version and returns its new version.
     */
    @Override
    public int increaseVersion() {
        return 0;
    }
}
