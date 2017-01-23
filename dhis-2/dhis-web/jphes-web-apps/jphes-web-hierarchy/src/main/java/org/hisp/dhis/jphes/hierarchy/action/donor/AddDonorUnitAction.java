package org.hisp.dhis.jphes.hierarchy.action.donor;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.IntegrationTest;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupAccess;
import org.hisp.dhis.user.UserGroupAccessService;
import org.hisp.dhis.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by @bangadennis on 05/01/17.
 */
public class AddDonorUnitAction implements Action
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

    @Autowired
    private ProgramService programService;

    @Autowired
    private UserGroupAccessService userGroupAccessService;

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

    private static final String NOPUBLICACCESS = "--------";
    private static final String READWRITEACCESS = "rw------";

    // -------------------------------------------------------------------------
    // Implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {


        if((userGroupService.getUserGroupByName( name ).size() == 0) && (selectedProgramList.size()>0))
        {
            //Dependent objects
            NationalUnit nationalUnit = nationalUnitService.getNationalUnit( id );
            CategoryOptionGroupSet categoryOptionGroupSetDonor = nationalUnit.getCategoryOptionGroupSet();

            DonorUnit donorUnit = new DonorUnit();

            donorUnit.setName( StringUtils.trimToNull( name ));
            donorUnit.setCode( StringUtils.trimToNull( code ) );
            donorUnit.setDescription( StringUtils.trimToNull( description ) );
            donorUnit.setEnabled( true );
            donorUnit.setShortName( StringUtils.trimToNull( shortName ) );
            donorUnit.setNationalUnit( nationalUnit );

            // Add programList

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                donorUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = new UserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );
            //Save UserGroup
            userGroupService.addUserGroup( userGroup );

            //CategoryOptionGroup
            CategoryOptionGroup categoryOptionGroup = new CategoryOptionGroup( );
            categoryOptionGroup.setName( StringUtils.trimToNull( name ) );
            categoryOptionGroup.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOptionGroup.setCode( StringUtils.trimToNull( code ) );
            categoryOptionGroup.setDataDimensionType( DataDimensionType.ATTRIBUTE );

            //save categoryOptionGroup
            categoryService.saveCategoryOptionGroup( categoryOptionGroup );

            //adding CategoryOptionGroup to DonorGroupSet
            categoryOptionGroupSetDonor.getMembers().add( categoryOptionGroup );
            categoryService.updateCategoryOptionGroupSet( categoryOptionGroupSetDonor );

            //Setting attributes
            donorUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            donorUnit.setCategoryOptionGroup( categoryService.getCategoryOptionGroup( categoryOptionGroup.getUid() ) );

            // Saving DonorUnit
            donorUnitService.addDonorUnit( donorUnit );

            //Adding donorUnit to NationUnit Set
            nationalUnit.getDonorUnits().add( donorUnit );
            nationalUnitService.updateNationalUnit( nationalUnit );

            // -------------------------------------------------------------------------
            // UserGroupAccess Sharing
            // -------------------------------------------------------------------------

            //Setting CategoryOptionGroup UserGroupAccesses

            UserGroupAccess accessDonor = new UserGroupAccess();
            accessDonor.setUserGroup( userGroup );
            accessDonor.setUid( userGroup.getUid() );
            accessDonor.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessDonor );

            UserGroupAccess accessNational = new UserGroupAccess();
            accessNational.setUserGroup( nationalUnit.getUserGroup() );
            accessNational.setUid( nationalUnit.getUserGroup().getUid() );
            accessNational.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessNational );

            // UserGroup sharing
            userGroup.setPublicAccess( NOPUBLICACCESS );
            userGroup.getUserGroupAccesses().add( accessDonor );
            userGroup.getUserGroupAccesses().add( accessNational );

            //UserGroupAccess(Donor, National) DonorOptionGroup sharing
            categoryOptionGroup.getUserGroupAccesses().add( accessDonor );
            categoryOptionGroup.getUserGroupAccesses().add( accessNational );
            categoryOptionGroup.setPublicAccess( NOPUBLICACCESS );

            //update adding userGroupAccess
            userGroupService.updateUserGroup( userGroup );
            categoryService.updateCategoryOptionGroup( categoryOptionGroup );


        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }

}
