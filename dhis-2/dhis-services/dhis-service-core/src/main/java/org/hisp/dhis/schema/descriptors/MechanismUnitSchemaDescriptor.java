package org.hisp.dhis.schema.descriptors;

//**

import com.google.common.collect.Lists;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaDescriptor;
import org.hisp.dhis.security.Authority;
import org.hisp.dhis.security.AuthorityType;
/**
 * @author bangadennis - 11/01/17.
 * */
public class MechanismUnitSchemaDescriptor implements SchemaDescriptor
{
    public static final String SINGULAR = "mechanismUnit";

    public static final String PLURAL = "mechanismUnits";

    public static final String API_ENDPOINT = "/" + PLURAL;

    @Override public Schema getSchema()
    {
        Schema schema = new Schema( MechanismUnit.class, SINGULAR, PLURAL );
        schema.setRelativeApiEndpoint( API_ENDPOINT );
        schema.setOrder( 2004 );

        schema.getAuthorities().add( new Authority( AuthorityType.CREATE, Lists.newArrayList( "F_MECHANISM_UNIT_ADD" ) ) );
        schema.getAuthorities().add( new Authority( AuthorityType.DELETE, Lists.newArrayList( "F_MECHANISM_UNIT_DELETE" ) ) );

        return schema;
    }
}