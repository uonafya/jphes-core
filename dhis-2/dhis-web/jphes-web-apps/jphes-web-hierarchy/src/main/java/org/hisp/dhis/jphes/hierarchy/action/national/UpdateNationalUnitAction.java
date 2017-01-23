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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by afya on 05/01/17.
 */
public class UpdateNationalUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private NationalUnitService nationalUnitService;

    public void setNationalUnitService(NationalUnitService nationalUnitService)
    {
        this.nationalUnitService =nationalUnitService;
    }

    private UserService userService;

    public void setUserService( UserService userService )
    {
        this.userService = userService;
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
            NationalUnit nationalUnit = nationalUnitService.getNationalUnit( id );

            nationalUnit.setName( StringUtils.trimToNull( name ) );
            nationalUnit.setCode( StringUtils.trimToNull( code ) );
            nationalUnit.setDescription( StringUtils.trimToNull( description ) );
            nationalUnit.setEnabled( true );
            nationalUnit.setShortName( StringUtils.trimToNull( shortName ) );

            // User group
            UserGroup userGroup = nationalUnit.getUserGroup();

            userGroup.setName( StringUtils.trimToNull( name ) );

            //Save UserGroup
            userGroupService.updateUserGroup( userGroup );

            //CategoryOptionGroupSet

            CategoryOptionGroupSet categoryOptionGroupSet = nationalUnit.getCategoryOptionGroupSet();
            categoryOptionGroupSet.setName( "A. Donors-" + StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            categoryOptionGroupSet.setDescription( StringUtils.trimToNull( description ) );
            categoryOptionGroupSet.setDataDimension( true );

            //save categoryOptionGroupSet
            categoryService.updateCategoryOptionGroupSet( categoryOptionGroupSet );

            //Mechanism Category
            DataElementCategory category = nationalUnit.getMechanismCategory();
            category.setName( "C. Mechanisms-" + StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            category.setDataDimension( true );

            // Update Category
            categoryService.updateDataElementCategory( category );

            //Mechanism CategoryCombo
            DataElementCategoryCombo categoryCombo = nationalUnit.getMechanismCombo();
            categoryCombo.setName( "Mechanisms Combo-" + StringUtils.abbreviate( StringUtils.trimToNull( shortName ), 30 ) );
            categoryCombo.setSkipTotal( true );

            //add Mechanism Category to Mechanism CategoryCombo
            categoryCombo.getCategories().clear();
            categoryCombo.getCategories().add( category );
            //Update CategoryCombo
            categoryService.updateDataElementCategoryCombo( categoryCombo );

            // Clear program list

            nationalUnit.getPrograms().clear();

            // update programList

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                nationalUnit.getPrograms().add( program );

            }

            nationalUnitService.updateNationalUnit( nationalUnit );
        }
        else
        {
            return ERROR;
        }

        return SUCCESS;
    }
}
