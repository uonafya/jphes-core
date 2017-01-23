package org.hisp.dhis.jphes.hierarchy.action.donor;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created @bangadennis on 12/01/17.
 */
public class GetDonorUnitAction implements Action
{    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    private DonorUnitService donorUnitService;

    public void setDonorUnitService(DonorUnitService donorUnitService){
        this.donorUnitService = donorUnitService;
    }


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

    private DonorUnit donorUnit;

    public DonorUnit getDonorUnit(){return donorUnit;}

    private List<Program> programList;

    public List<Program> getProgramList(){return programList;}

    private UserGroup userGroup;

    public UserGroup getUserGroup(){return userGroup;}

    private NationalUnit nationalUnit;

    public NationalUnit getNationalUnit(){return nationalUnit;}
    private CategoryOptionGroup categoryOptionGroup;

    public CategoryOptionGroup getCategoryOptionGroup(){
        return categoryOptionGroup;
    }

    private CategoryOptionGroupSet categoryOptionGroupSet;

    public CategoryOptionGroupSet getCategoryOptionGroupSet(){return categoryOptionGroupSet;}


    // -------------------------------------------------------------------------
    // Action Implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        donorUnit = donorUnitService.getDonorUnit( id );

        userGroup = donorUnit.getUserGroup();

        nationalUnit = donorUnit.getNationalUnit();

        categoryOptionGroup = donorUnit.getCategoryOptionGroup();

        programList = new ArrayList<>(donorUnit.getPrograms());

        Collections.sort( programList );

        return SUCCESS;

    }
}
