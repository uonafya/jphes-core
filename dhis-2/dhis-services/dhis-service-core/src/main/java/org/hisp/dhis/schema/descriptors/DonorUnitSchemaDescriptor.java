package org.hisp.dhis.schema.descriptors;

import com.google.common.collect.Lists;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaDescriptor;
import org.hisp.dhis.security.Authority;
import org.hisp.dhis.security.AuthorityType;

/**
 * @author bangadennis on 11/01/17.
 */
public class DonorUnitSchemaDescriptor implements SchemaDescriptor
{
    public static final String SINGULAR = "donorUnit";

    public static final String PLURAL = "donorUnits";

    public static final String API_ENDPOINT = "/" + PLURAL;

    @Override public Schema getSchema()
    {
        Schema schema = new Schema( DonorUnit.class, SINGULAR, PLURAL );
        schema.setRelativeApiEndpoint( API_ENDPOINT );
        schema.setOrder( 3010 );

        schema.getAuthorities().add( new Authority( AuthorityType.CREATE, Lists.newArrayList( "F_DONOR_UNIT_ADD" ) ) );
        schema.getAuthorities().add( new Authority( AuthorityType.DELETE, Lists.newArrayList( "F_DONOR_UNIT_DELETE" ) ) );

        return schema;
    }
}
