package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.dataelement.DataElementCategoryCombo;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.hisp.dhis.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by afya on 05/01/17.
 */
public class AddNationalUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

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


        if(userGroupService.getUserGroupByName( name ).size() == 0 && selectedProgramList.size()>0 )
        {
            NationalUnit nationalUnit = new NationalUnit();

            nationalUnit.setName( StringUtils.trimToNull( name ));
            nationalUnit.setCode( StringUtils.trimToNull( code ) );
            nationalUnit.setDescription( StringUtils.trimToNull( description ) );
            nationalUnit.setEnabled( true );
            nationalUnit.setShortName( StringUtils.trimToNull( shortName ) );

            // User group
            UserGroup userGroup = new UserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );

            //Save UserGroup
            userGroupService.addUserGroup( userGroup );

            //CategoryOptionGroupSet Donor

            CategoryOptionGroupSet categoryOptionGroupSetDonor = new CategoryOptionGroupSet( );
            categoryOptionGroupSetDonor.setName(  "A. Donor Units-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 )  );
            categoryOptionGroupSetDonor.setDescription( StringUtils.trimToNull( description ) );
            categoryOptionGroupSetDonor.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryOptionGroupSetDonor.setDataDimension( true );

            //save categoryOptionGroupSetDonor
            categoryService.saveCategoryOptionGroupSet( categoryOptionGroupSetDonor );

            //CategoryOptionGroupSet Agency

            CategoryOptionGroupSet categoryOptionGroupSetAgency = new CategoryOptionGroupSet( );
            categoryOptionGroupSetAgency.setName(  "B. Agency Units-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 )  );
            categoryOptionGroupSetAgency.setDescription( StringUtils.trimToNull( description ) );
            categoryOptionGroupSetAgency.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryOptionGroupSetAgency.setDataDimension( true );

            //save categoryOptionGroupSetAgency
            categoryService.saveCategoryOptionGroupSet( categoryOptionGroupSetAgency );


            //Mechanism Category
            DataElementCategory category = new DataElementCategory(  );
            category.setName( "C. Mechanism Units-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            category.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            category.setDataDimension( true );

            // Save Category
            categoryService.addDataElementCategory( category );

            //Mechanism CategoryCombo
            DataElementCategoryCombo categoryCombo = new DataElementCategoryCombo(  );
            categoryCombo.setName( "MechanismUnit Combo-"+ StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            categoryCombo.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryCombo.setSkipTotal( true );
            //add Mechanism Category to Mechanism CategoryCombo
            categoryCombo.getCategories().add( categoryService.getDataElementCategory( category.getUid() )  );
            //Save CategoryCombo
            categoryService.addDataElementCategoryCombo( categoryCombo );

            //Setting attributes

            nationalUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            nationalUnit.setCategoryOptionGroupSet( categoryService.getCategoryOptionGroupSet( categoryOptionGroupSetDonor.getUid() ) );
            nationalUnit.setCategoryOptionGroupSetAgency( categoryService.getCategoryOptionGroupSet( categoryOptionGroupSetAgency.getUid() ) );
            nationalUnit.setMechanismCategory( categoryService.getDataElementCategory( category.getUid() ) );
            nationalUnit.setMechanismCombo( categoryService.getDataElementCategoryCombo( categoryCombo.getUid() ) );

            // Saving NationalUnit
            nationalUnitService.addNationalUnit( nationalUnit );

            // Add programList
            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                nationalUnit.getPrograms().add( program );

            }

            //update with programs
            nationalUnitService.updateNationalUnit( nationalUnit );

        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }

}
