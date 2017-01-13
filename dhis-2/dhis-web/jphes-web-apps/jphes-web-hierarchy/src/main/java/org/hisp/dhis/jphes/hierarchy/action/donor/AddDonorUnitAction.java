package org.hisp.dhis.jphes.hierarchy.action.donor;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.IntegrationTest;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.hisp.dhis.user.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by afya on 05/01/17.
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


        if(userGroupService.getUserGroupByName( name ).size() == 0 )
        {
            NationalUnit nationalUnit = nationalUnitService.getNationalUnit( id );

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

            //CategoryOptionGroupSet

            CategoryOptionGroupSet categoryOptionGroupSet = new CategoryOptionGroupSet( );
            categoryOptionGroupSet.setName(  "A. Donors-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 )  );
            categoryOptionGroupSet.setDescription( StringUtils.trimToNull( description ) );
            categoryOptionGroupSet.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryOptionGroupSet.setDataDimension( true );

            //save categoryOptionGroupSet
            categoryService.saveCategoryOptionGroupSet( categoryOptionGroupSet );

            //Mechanism Category
            DataElementCategory category = new DataElementCategory(  );
            category.setName( "C. Mechanisms-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            category.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            category.setDataDimension( true );

            // Save Category
            categoryService.addDataElementCategory( category );

            //Mechanism CategoryCombo
            DataElementCategoryCombo categoryCombo = new DataElementCategoryCombo(  );
            categoryCombo.setName( "Mechanisms Combo-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            categoryCombo.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryCombo.setSkipTotal( true );
            //add Mechanism Category to Mechanism CategoryCombo
            categoryCombo.getCategories().add( categoryService.getDataElementCategory( category.getUid() )  );
            //Save CategoryCombo
            categoryService.addDataElementCategoryCombo( categoryCombo );

            //Setting attributes

            nationalUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            nationalUnit.setCategoryOptionGroupSet( categoryService.getCategoryOptionGroupSet( categoryOptionGroupSet.getUid() ) );
            nationalUnit.setMechanismCategory( categoryService.getDataElementCategory( category.getUid() ) );
            nationalUnit.setMechanismCombo( categoryService.getDataElementCategoryCombo( categoryCombo.getUid() ) );

            // Saving NationalUnit
            nationalUnitService.addNationalUnit( nationalUnit );
        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }

}
