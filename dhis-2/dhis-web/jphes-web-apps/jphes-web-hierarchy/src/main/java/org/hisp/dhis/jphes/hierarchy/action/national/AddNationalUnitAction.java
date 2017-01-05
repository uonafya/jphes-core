package org.hisp.dhis.jphes.hierarchy.action.national;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.hisp.dhis.user.UserService;
import org.nfunk.jep.function.Str;

import java.util.ArrayList;
import java.util.Collection;

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
    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private String name;

    public void setName( String name )
    {
        this.name = name;
    }

    private String code;

    public void setCode(String code){ this.code = code; }

    private String shortname;

    public void setShortname(String shortname){ this.shortname = shortname; }

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
        return SUCCESS;
    }

}
