package org.hisp.dhis.schema.descriptors;

import com.google.common.collect.Lists;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.schema.Schema;
import org.hisp.dhis.schema.SchemaDescriptor;
import org.hisp.dhis.security.Authority;
import org.hisp.dhis.security.AuthorityType;

/**
 * Created by afya on 22/01/17.
 */
public class JphesProgramSchemaDescriptor implements SchemaDescriptor
{
    public static final String SINGULAR = "jphesProgram";

    public static final String PLURAL = "jphesPrograms";

    public static final String API_ENDPOINT = "/" + PLURAL;

    @Override public Schema getSchema()
    {
        Schema schema = new Schema( Program.class, SINGULAR, PLURAL );
        schema.setRelativeApiEndpoint( API_ENDPOINT );
        schema.setOrder( 2003 );

        schema.getAuthorities().add( new Authority( AuthorityType.CREATE, Lists.newArrayList( "F_JPHESPROGRAM_ADD" ) ) );
        schema.getAuthorities().add( new Authority( AuthorityType.DELETE, Lists.newArrayList( "F_JPHESPROGRAM_DELETE" ) ) );

        return schema;
    }
}
