package org.hisp.dhis.jphes.hierarchy.action.agency;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.common.DataDimensionType;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.program.ProgramService;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by @bangadennis on 05/01/17.
 */
public class AddAgencyUnitAction implements Action
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    @Autowired
    private AgencyUnitService agencyUnitService;

    @Autowired
    private DonorUnitService donorUnitService;

    @Autowired
    private NationalUnitService nationalUnitService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private DataElementCategoryService categoryService;

    @Autowired
    private ProgramService programService;


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


        if((userGroupService.getUserGroupByName( name ).size() == 0) && (selectedProgramList.size()>0))
        {
            //Dependent objects
            DonorUnit donorUnit = donorUnitService.getDonorUnit( id );
            NationalUnit nationalUnit = donorUnit.getNationalUnit();
            CategoryOptionGroupSet categoryOptionGroupSetAgency = nationalUnit.getCategoryOptionGroupSetAgency();

            AgencyUnit agencyUnit= new AgencyUnit();

            agencyUnit.setName( StringUtils.trimToNull( name ));
            agencyUnit.setCode( StringUtils.trimToNull( code ) );
            agencyUnit.setDescription( StringUtils.trimToNull( description ) );
            agencyUnit.setEnabled( true );
            agencyUnit.setShortName( StringUtils.trimToNull( shortName ) );
            agencyUnit.setDonorUnit( donorUnit );

            // Add programList

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                agencyUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = new UserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );
            //Save UserGroup
            userGroupService.addUserGroup( userGroup );

            //CategoryOptionGroup Agency
            CategoryOptionGroup categoryOptionGroup = new CategoryOptionGroup( );
            categoryOptionGroup.setName( StringUtils.trimToNull( name ) );
            categoryOptionGroup.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOptionGroup.setCode( StringUtils.trimToNull( code ) );
            categoryOptionGroup.setDataDimensionType( DataDimensionType.ATTRIBUTE );

            //save categoryOptionGroup
            categoryService.saveCategoryOptionGroup( categoryOptionGroup );

            //adding CategoryOptionGroup to AgencyGroupSet
            categoryOptionGroupSetAgency.getMembers().add( categoryOptionGroup );
            categoryService.updateCategoryOptionGroupSet( categoryOptionGroupSetAgency );

            //Setting attributes
            agencyUnit.setUserGroup( userGroupService.getUserGroup( userGroup.getUid() ));
            agencyUnit.setCategoryOptionGroup( categoryService.getCategoryOptionGroup( categoryOptionGroup.getUid() ) );

            // Save AgencyUnit
            agencyUnitService.addAgencyUnit( agencyUnit );

            //Adding agencyUnit to DonorUnit Set
            donorUnit.getAgencyUnits().add( agencyUnit );
            donorUnitService.updateDonorUnit( donorUnit );
        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }
}
