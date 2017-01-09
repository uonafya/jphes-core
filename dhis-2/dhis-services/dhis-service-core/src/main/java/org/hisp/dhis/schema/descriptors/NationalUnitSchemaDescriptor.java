package org.hisp.dhis.schema.descriptors;

import com.google.common.collect.Lists;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaDescriptor;
import org.hisp.dhis.security.Authority;
import org.hisp.dhis.security.AuthorityType;

/**
 * @auther bangadennis on 10/01/17.
 */
public class NationalUnitSchemaDescriptor implements SchemaDescriptor
{
    public static final String SINGULAR = "nationalUnit";

    public static final String PLURAL = "nationalUnits";

    public static final String API_ENDPOINT = "/" + PLURAL;

    @Override public Schema getSchema()
    {
        Schema schema = new Schema( NationalUnit.class, SINGULAR, PLURAL );
        schema.setRelativeApiEndpoint( API_ENDPOINT );
        schema.setOrder( 2001 );

        schema.getAuthorities().add( new Authority( AuthorityType.CREATE, Lists.newArrayList( "F_NATIONAL_UNIT_ADD" ) ) );
        schema.getAuthorities().add( new Authority( AuthorityType.DELETE, Lists.newArrayList( "F_NATIONAL_UNIT_DELETE" ) ) );

        return schema;
    }
}
