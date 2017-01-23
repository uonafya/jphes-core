package org.hisp.dhis.jphes.hierarchy.action.donor;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by afya on 12/01/17.
 */
public class UpdateDonorUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    private DonorUnitService donorUnitService;

    public void setDonorUnitService(DonorUnitService donorUnitService){
        this.donorUnitService = donorUnitService;
    }

    private NationalUnitService nationalUnitService;

    public void setNationalUnitService(NationalUnitService nationalUnitService)
    {
        this.nationalUnitService =nationalUnitService;
    }

    private UserGroupService userGroupService;

    public void setUserGroupService(UserGroupService userGroupService){
        this.userGroupService = userGroupService;
    }

    private DataElementCategoryService categoryService;

    public void setCategoryService(DataElementCategoryService categoryService){

        this.categoryService = categoryService;
    }

    private ProgramService programService;

    public void setProgramService(ProgramService programService){

        this.programService = programService;
    }


    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId( Integer id )
    {
        this.id = id;
    }

    private String name;

    public void setName( String name )
    {
        this.name = name;
    }

    private String code;

    public void setCode(String code){ this.code = code; }

    private String shortName;

    public void setShortName(String shortName){ this.shortName = shortName; }

    private String description;

    public void setDescription(String description){this.description = description;}


    private Collection<String> selectedProgramList = new ArrayList<>();

    public void setSelectedProgramList( Collection<String> selectedProgramList )
    {
        this.selectedProgramList = selectedProgramList;
    }


    // -------------------------------------------------------------------------
    // Implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {
        if(selectedProgramList.size()>0)
        {
            DonorUnit donorUnit = donorUnitService.getDonorUnit( id );

            //Dependent objects
            NationalUnit nationalUnit = donorUnit.getNationalUnit();
            CategoryOptionGroupSet categoryOptionGroupSetDonor = nationalUnit.getCategoryOptionGroupSet();

            //Updating donor attributes
            donorUnit.setName( StringUtils.trimToNull( name ));
            donorUnit.setCode( StringUtils.trimToNull( code ) );
            donorUnit.setDescription( StringUtils.trimToNull( description ) );
            donorUnit.setEnabled( true );
            donorUnit.setShortName( StringUtils.trimToNull( shortName ) );

            // Add programList

            donorUnit.getPrograms().clear();

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                donorUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = donorUnit.getUserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );
            //update UserGroup
            userGroupService.updateUserGroup( userGroup );

            //CategoryOptionGroup
            CategoryOptionGroup categoryOptionGroup = donorUnit.getCategoryOptionGroup();
            categoryOptionGroup.setName( StringUtils.trimToNull( name ) );
            categoryOptionGroup.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOptionGroup.setCode( StringUtils.trimToNull( code ) );

            //save categoryOptionGroup
            categoryService.updateCategoryOptionGroup(categoryOptionGroup);

            //adding CategoryOptionGroup to DonorGroupSet if missing
            if(!(categoryOptionGroupSetDonor.getMembers().contains( categoryOptionGroup ))){
                categoryOptionGroupSetDonor.getMembers().add( categoryOptionGroup );
                categoryService.updateCategoryOptionGroupSet( categoryOptionGroupSetDonor );
            }

            // Saving DonorUnit
            donorUnitService.updateDonorUnit( donorUnit );

            //Adding donorUnit to NationUnit Set if missing
            if(!(nationalUnit.getDonorUnits().contains( donorUnit ))){

                nationalUnit.getDonorUnits().add( donorUnit );
                nationalUnitService.updateNationalUnit( nationalUnit );
            }
        }
        else {
            return ERROR;
        }


        return SUCCESS;
    }
}
