package org.hisp.dhis.jphes.program;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.BaseDimensionalItemObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.VersionedObject;
import org.hisp.dhis.dataset.DataSetElement;
import org.hisp.dhis.indicator.Indicator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<DataSetElement> dataSetElements = new HashSet<>();

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
