package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.DataElementCategoryOption;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by afya on 13/01/17.
 */
public class GetMechanismUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MechanismUnitService mechanismUnitService;

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId( Integer id )
    {
        this.id = id;
    }

    // -------------------------------------------------------------------------
    // Output
    // -------------------------------------------------------------------------

    private MechanismUnit mechanismUnit;

    public MechanismUnit getMechanismUnit(){return mechanismUnit;}

    private List<Program> programList;

    public List<Program> getProgramList(){return programList;}

    private UserGroup userGroup;

    public UserGroup getUserGroup(){return userGroup;}

    private AgencyUnit agencyUnit;

    public AgencyUnit getAgencyUnit(){return agencyUnit;}

    private DataElementCategoryOption categoryOption;

    public DataElementCategoryOption getCategoryOption(){
        return categoryOption;
    }


    // -------------------------------------------------------------------------
    // Action Implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        mechanismUnit = mechanismUnitService.getMechanismUnit( id );

        userGroup = mechanismUnit.getUserGroup();

        agencyUnit = mechanismUnit.getAgencyUnit();

        categoryOption = mechanismUnit.getCategoryOption();

        programList = new ArrayList<>(mechanismUnit.getPrograms());

        Collections.sort( programList );

        return SUCCESS;

    }
}
