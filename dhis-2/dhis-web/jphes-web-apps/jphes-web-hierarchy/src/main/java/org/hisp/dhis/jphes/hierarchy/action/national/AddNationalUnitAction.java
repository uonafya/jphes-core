package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.hisp.dhis.user.UserService;
import org.nfunk.jep.function.Str;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


        if(userGroupService.getUserGroupCountByName( name ) == 0 )
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

            //CategoryOptionGroupSet

            CategoryOptionGroupSet categoryOptionGroupSet = new CategoryOptionGroupSet( );
            categoryOptionGroupSet.setName( StringUtils.trimToNull( name ) );
            categoryOptionGroupSet.setDescription( StringUtils.trimToNull( description ) );
            categoryOptionGroupSet.setDataDimensionType( DataDimensionType.ATTRIBUTE );
            categoryOptionGroupSet.setDataDimension( true );

            //save categoryOptionGroupSet
            categoryService.saveCategoryOptionGroupSet( categoryOptionGroupSet );
            // program list

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                nationalUnit.getPrograms().add( program );

            }

            nationalUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            nationalUnit.setCategoryOptionGroupSet( categoryService.getCategoryOptionGroupSet( categoryOptionGroupSet.getUid() ) );

            nationalUnitService.addNationalUnit( nationalUnit );
        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }

}
