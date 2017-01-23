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
 * Created by @bangadennis on 13/01/17.
 */
public class UpdateAgencyUnitAction implements Action
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


        if(selectedProgramList.size()>0)
        {
            AgencyUnit agencyUnit= agencyUnitService.getAgencyUnit( id );
            //Dependent objects
            DonorUnit donorUnit = agencyUnit.getDonorUnit();
            NationalUnit nationalUnit = donorUnit.getNationalUnit();
            CategoryOptionGroupSet categoryOptionGroupSetAgency = nationalUnit.getCategoryOptionGroupSetAgency();

            agencyUnit.setName( StringUtils.trimToNull( name ));
            agencyUnit.setCode( StringUtils.trimToNull( code ) );
            agencyUnit.setDescription( StringUtils.trimToNull( description ) );
            agencyUnit.setEnabled( true );
            agencyUnit.setShortName( StringUtils.trimToNull( shortName ) );

            // Add programList

            agencyUnit.getPrograms().clear();

            for ( String id : selectedProgramList )
            {
                Program program = programService.getProgram( id );
                agencyUnit.getPrograms().add( program );

            }

            // User group
            UserGroup userGroup = agencyUnit.getUserGroup();

            userGroup.setName( StringUtils.trimToNull(name) );
            //Update UserGroup
            userGroupService.updateUserGroup( userGroup );

            //CategoryOptionGroup Agency
            CategoryOptionGroup categoryOptionGroup = agencyUnit.getCategoryOptionGroup();
            categoryOptionGroup.setName( StringUtils.trimToNull( name ) );
            categoryOptionGroup.setShortName( StringUtils.trimToNull( shortName ) );
            categoryOptionGroup.setCode( StringUtils.trimToNull( code ) );

            //save categoryOptionGroup
            categoryService.updateCategoryOptionGroup( categoryOptionGroup );

            //adding CategoryOptionGroup to AgencyGroupSet
            if(!(categoryOptionGroupSetAgency.getMembers().contains( categoryOptionGroup )))
            {
                categoryOptionGroupSetAgency.getMembers().add( categoryOptionGroup );
                categoryService.updateCategoryOptionGroupSet( categoryOptionGroupSetAgency );
            }

            // update AgencyUnit
            agencyUnitService.updateAgencyUnit( agencyUnit );

            //Adding agencyUnit to DonorUnit Set
            if(!(donorUnit.getAgencyUnits().contains( agencyUnit ))){
                donorUnit.getAgencyUnits().add( agencyUnit );
                donorUnitService.updateDonorUnit( donorUnit );
            }
        }
        else
        {
            return ERROR;
        }


        return SUCCESS;
    }
}
