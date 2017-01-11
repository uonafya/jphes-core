package org.hisp.dhis.schema.descriptors;

import com.google.common.collect.Lists;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaDescriptor;
import org.hisp.dhis.security.Authority;
import org.hisp.dhis.security.AuthorityType;

/**
 * @author bangadennis - 11/01/17.
 */
public class AgencyUnitSchemaDescriptor implements SchemaDescriptor
{
    public static final String SINGULAR = "agencyUnit";

    public static final String PLURAL = "agencyUnits";

    public static final String API_ENDPOINT = "/" + PLURAL;

    @Override public Schema getSchema()
    {
        Schema schema = new Schema( AgencyUnit.class, SINGULAR, PLURAL );
        schema.setRelativeApiEndpoint( API_ENDPOINT );
        schema.setOrder( 2003 );

        schema.getAuthorities().add( new Authority( AuthorityType.CREATE, Lists.newArrayList( "F_AGENCY_UNIT_ADD" ) ) );
        schema.getAuthorities().add( new Authority( AuthorityType.DELETE, Lists.newArrayList( "F_AGENCY_UNIT_DELETE" ) ) );

        return schema;
    }
}
