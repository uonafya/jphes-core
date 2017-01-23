package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.dataelement.DataElementCategoryOption;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.i18n.I18nFormat;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupAccess;
import org.hisp.dhis.user.UserGroupAccessService;
import org.hisp.dhis.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by afya on 13/01/17.
 */
public class UpdateMechanismUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    @Autowired MechanismUnitService mechanismUnitService;

    @Autowired
    private AgencyUnitService agencyUnitService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private DataElementCategoryService categoryService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private UserGroupAccessService userGroupAccessService;

    private I18nFormat format;

    public void setFormat( I18nFormat format )
    {
        this.format = format;
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

    private String partner;

    public void setPartner( String partner )
    {
        this.partner = partner;
    }

    private String code;

    public void setCode( String code )
    {
        this.code = code;
    }

    private String shortName;

    public void setShortName( String shortName )
    {
        this.shortName = shortName;
    }

    private String description;

    public void setDescription( String description )
    {
        this.description = description;
    }

    private String startDate;

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    private String endDate;

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


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


        if ( selectedProgramList.size() > 0 )
        {
            MechanismUnit mechanismUnit = mechanismUnitService.getMechanismUnit( id );
            //Dependent objects
            AgencyUnit agencyUnit = mechanismUnit.getAgencyUnit();
            DonorUnit donorUnit = agencyUnit.getDonorUnit();
            NationalUnit nationalUnit = donorUnit.getNationalUnit();
            CategoryOptionGroup categoryOptionGroupAgency = agencyUnit.getCategoryOptionGroup();
            CategoryOptionGroup categoryOptionGroupDonor = donorUnit.getCategoryOptionGroup();
            DataElementCategory mechanismCategory = nationalUnit.getMechanismCategory();


            mechanismUnit.setName( StringUtils.trimToNull( name ) );
            mechanismUnit.setCode( StringUtils.trimToNull( code ) );
            mechanismUnit.setPartner( StringUtils.trimToNull( partner ) );
            mechanismUnit.setDescription( StringUtils.trimToNull( description ) );
            mechanismUnit.setEnabled( true );
            mechanismUnit.setShortName( StringUtils.trimToNull( shortName ) );

            // Add programList

            mechanismUnit.getPrograms().clear();

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                mechanismUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = mechanismUnit.getUserGroup();

            userGroup.setName( StringUtils.trimToNull( name ) );
            //Update UserGroup
            userGroupService.updateUserGroup( userGroup );

            //CategoryOption
            DataElementCategoryOption categoryOption = mechanismUnit.getCategoryOption();
            categoryOption.setName( StringUtils.trimToNull( name ) );
            categoryOption.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOption.setCode( StringUtils.trimToNull( code ) );
            categoryOption.setStartDate( format.parseDate( startDate ) );
            categoryOption.setEndDate( format.parseDate( endDate ) );

            //update categoryOption
            categoryService.updateDataElementCategoryOption( categoryOption );

            //Update categoryOption to MechanismCategory
            if(!mechanismCategory.getCategoryOptions().contains( categoryOption )){
                mechanismCategory.getCategoryOptions().add( categoryOption );
                categoryService.updateDataElementCategory( mechanismCategory );
            }

            //adding CategoryOption to AgencyOptionGroup
            if ( !categoryOptionGroupAgency.getMembers().contains( categoryOption ) )
            {
                categoryOptionGroupAgency.getMembers().add( categoryOption );
                categoryService.updateCategoryOptionGroup( categoryOptionGroupAgency );

            }

            //adding CategoryOption to DonorOptionGroup
            if ( !categoryOptionGroupDonor.getMembers().contains( categoryOption ) )
            {
                categoryOptionGroupDonor.getMembers().add( categoryOption );
                categoryService.updateCategoryOptionGroup( categoryOptionGroupDonor );
            }

            // Save MechanismUnit
            mechanismUnitService.updateMechanismUnit( mechanismUnit );

            //Adding mechanismUnit to AgencyUnit Set
            if(!agencyUnit.getMechanismUnits().contains( mechanismUnit )){

                agencyUnit.getMechanismUnits().add( mechanismUnit );
                agencyUnitService.updateAgencyUnit( agencyUnit );
            }

        }
        else
        {
            return ERROR;
        }

        return SUCCESS;
    }
}
