package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategory;
import org.hisp.dhis.dataelement.DataElementCategoryOption;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.i18n.I18nFormat;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupAccess;
import org.hisp.dhis.user.UserGroupAccessService;
import org.hisp.dhis.user.UserGroupService;
import org.nfunk.jep.function.Str;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.soap.SAAJResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by afya on 05/01/17.
 */
public class AddMechanismUnitAction implements Action
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

    public void setPartner(String partner){ this.partner = partner;}

    private String code;

    public void setCode(String code){ this.code = code; }

    private String shortName;

    public void setShortName(String shortName){ this.shortName = shortName; }

    private String description;

    public void setDescription(String description){this.description = description;}

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


        if((userGroupService.getUserGroupByName( name ).size() == 0) && (selectedProgramList.size()>0))
        {
            //Dependent objects
            AgencyUnit agencyUnit = agencyUnitService.getAgencyUnit( id );
            DonorUnit donorUnit = agencyUnit.getDonorUnit();
            NationalUnit nationalUnit = donorUnit.getNationalUnit();
            CategoryOptionGroup categoryOptionGroupAgency = agencyUnit.getCategoryOptionGroup();
            CategoryOptionGroup categoryOptionGroupDonor = donorUnit.getCategoryOptionGroup();
            DataElementCategory mechanismCategory = nationalUnit.getMechanismCategory();

            MechanismUnit mechanismUnit = new MechanismUnit();

            mechanismUnit.setName( StringUtils.trimToNull( name ));
            mechanismUnit.setCode( StringUtils.trimToNull( code ) );
            mechanismUnit.setPartner( StringUtils.trimToNull( partner ) );
            mechanismUnit.setDescription( StringUtils.trimToNull( description ) );
            mechanismUnit.setEnabled( true );
            mechanismUnit.setShortName( StringUtils.trimToNull( shortName ) );
            mechanismUnit.setAgencyUnit( agencyUnit );

            // Add programList

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                mechanismUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = new UserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );
            //Save UserGroup
            userGroupService.addUserGroup( userGroup );

            //CategoryOption
            DataElementCategoryOption categoryOption = new DataElementCategoryOption( );
            categoryOption.setName( StringUtils.trimToNull( name ) );
            categoryOption.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOption.setCode( StringUtils.trimToNull( code ) );
            categoryOption.setStartDate( format.parseDate(startDate) );
            categoryOption.setEndDate( format.parseDate( endDate ) );

            //save categoryOption
            categoryService.addDataElementCategoryOption( categoryOption );


            //Add to Mechanism Category
            mechanismCategory.getCategoryOptions().add( categoryOption );
            categoryService.updateDataElementCategory( mechanismCategory );

            //adding CategoryOption to AgencyOptionGroup
            categoryOptionGroupAgency.getMembers().add( categoryOption );
            categoryService.updateCategoryOptionGroup( categoryOptionGroupAgency );

            //adding CategoryOption to DonorOptionGroup
            categoryOptionGroupDonor.getMembers().add( categoryOption );
            categoryService.updateCategoryOptionGroup( categoryOptionGroupDonor );


            //Setting attributes
            mechanismUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            mechanismUnit.setCategoryOption( categoryOption );

            // Save MechanismUnit
            mechanismUnitService.addMechanismUnit( mechanismUnit );

            //Adding mechanismUnit to AgencyUnit Set
            agencyUnit.getMechanismUnits().add( mechanismUnit );
            agencyUnitService.updateAgencyUnit( agencyUnit );

            // -------------------------------------------------------------------------
            // UserGroupAccess Sharing
            // -------------------------------------------------------------------------
            //Setting CategoryOption UserGroupAccesses

            UserGroupAccess accessMechanism = new UserGroupAccess();
            accessMechanism.setUserGroup( userGroup );
            accessMechanism.setUid( userGroup.getUid() );
            accessMechanism.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessMechanism );

            UserGroupAccess accessAgency = new UserGroupAccess();
            accessAgency.setUserGroup( agencyUnit.getUserGroup() );
            accessAgency.setUid( agencyUnit.getUserGroup().getUid() );
            accessAgency.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessAgency );

            UserGroupAccess accessDonor = new UserGroupAccess();
            accessDonor.setUserGroup( donorUnit.getUserGroup() );
            accessDonor.setUid( donorUnit.getUserGroup().getUid() );
            accessDonor.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessDonor );

            UserGroupAccess accessNational = new UserGroupAccess();
            accessNational.setUserGroup( nationalUnit.getUserGroup() );
            accessNational.setUid( nationalUnit.getUserGroup().getUid() );
            accessNational.setAccess( READWRITEACCESS );
            userGroupAccessService.addUserGroupAccess( accessNational );

            //UserGroup sharing
            userGroup.setPublicAccess( NOPUBLICACCESS );
            userGroup.getUserGroupAccesses().add( accessMechanism );
            userGroup.getUserGroupAccesses().add( accessAgency );
            userGroup.getUserGroupAccesses().add( accessDonor );
            userGroup.getUserGroupAccesses().add( accessNational );

            categoryOption.getUserGroupAccesses().add( accessMechanism );
            categoryOption.getUserGroupAccesses().add( accessAgency );
            categoryOption.getUserGroupAccesses().add( accessDonor );
            categoryOption.getUserGroupAccesses().add( accessNational );
            //Setting NOPUBLICACCESS
            categoryOption.setPublicAccess( NOPUBLICACCESS );

            //Updating with UserGroupAccess sharing
            userGroupService.updateUserGroup( userGroup );
            categoryService.updateDataElementCategoryOption( categoryOption );

        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }
}
