package org.hisp.dhis.program;

/*
 * Copyright (c) 2004-2016, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import org.hisp.dhis.analytics.AggregationType;
import org.hisp.dhis.common.BaseDataDimensionalItemObject;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DimensionItemType;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.common.MergeMode;
import org.hisp.dhis.common.RegexUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Chau Thu Tran
 */
@JacksonXmlRootElement( localName = "programIndicator", namespace = DxfNamespaces.DXF_2_0 )
public class ProgramIndicator
    extends BaseDataDimensionalItemObject
{
    public static final String SEPARATOR_ID = "\\.";
    public static final String SEP_OBJECT = ":";
    public static final String KEY_DATAELEMENT = "#";
    public static final String KEY_ATTRIBUTE = "A";
    public static final String KEY_PROGRAM_VARIABLE = "V";
    public static final String KEY_CONSTANT = "C";

    public static final String VAR_EXECUTION_DATE = "execution_date";
    public static final String VAR_DUE_DATE = "due_date";
    public static final String VAR_INCIDENT_DATE = "incident_date";
    public static final String VAR_ENROLLMENT_DATE = "enrollment_date";
    public static final String VAR_CURRENT_DATE = "current_date";
    public static final String VAR_VALUE_COUNT = "value_count";
    public static final String VAR_ZERO_POS_VALUE_COUNT = "zero_pos_value_count";
    public static final String VAR_EVENT_COUNT = "event_count";
    public static final String VAR_ENROLLMENT_COUNT = "enrollment_count";
    public static final String VAR_TEI_COUNT = "tei_count";

    public static final String EXPRESSION_PREFIX_REGEXP = KEY_DATAELEMENT + "|" + KEY_ATTRIBUTE + "|" + KEY_PROGRAM_VARIABLE + "|" + KEY_CONSTANT;
    public static final String EXPRESSION_REGEXP = "(" + EXPRESSION_PREFIX_REGEXP + ")\\{([\\w\\_]+)" + SEPARATOR_ID + "?(\\w*)\\}";
    public static final String SQL_FUNC_REGEXP = "d2:(.+?)\\((.*?)\\)";
    public static final String ARGS_SPLIT = ",";
    public static final String ATTRIBUTE_REGEX = KEY_ATTRIBUTE + "\\{(\\w{11})\\}";
    public static final String DATAELEMENT_REGEX = KEY_DATAELEMENT + "\\{(\\w{11})" + SEPARATOR_ID + "(\\w{11})\\}";
    public static final String VARIABLE_REGEX = KEY_PROGRAM_VARIABLE + "\\{([\\w\\_]+)}";
    public static final String PROGRAMSTAGE_DATAELEMENT_GROUP_REGEX = KEY_DATAELEMENT + "\\{(\\w{11}" + SEPARATOR_ID + "\\w{11})\\}";
    public static final String VALUECOUNT_REGEX = "V\\{(" + VAR_VALUE_COUNT + "|" + VAR_ZERO_POS_VALUE_COUNT + ")\\}";
    public static final String EQUALSEMPTY = " *== *'' *";
    public static final String EQUALSZERO = " *== *0 *";
    public static final String EXPRESSION_EQUALSZEROOREMPTY_REGEX = EXPRESSION_REGEXP + "(" + EQUALSEMPTY + "|" + EQUALSZERO + ")?";
    
    
    public static final Pattern EXPRESSION_PATTERN = Pattern.compile( EXPRESSION_REGEXP );
    public static final Pattern EXPRESSION_EQUALSZEROOREMPTY_PATTERN = Pattern.compile( EXPRESSION_EQUALSZEROOREMPTY_REGEX );
    public static final Pattern SQL_FUNC_PATTERN = Pattern.compile( SQL_FUNC_REGEXP );

    public static final Pattern DATAELEMENT_PATTERN = Pattern.compile( DATAELEMENT_REGEX );
    public static final Pattern PROGRAMSTAGE_DATAELEMENT_GROUP_PATTERN = Pattern.compile( PROGRAMSTAGE_DATAELEMENT_GROUP_REGEX );
    public static final Pattern ATTRIBUTE_PATTERN = Pattern.compile( ATTRIBUTE_REGEX );
    public static final Pattern VARIABLE_PATTERN = Pattern.compile( VARIABLE_REGEX );
    public static final Pattern VALUECOUNT_PATTERN = Pattern.compile( VALUECOUNT_REGEX );
    
    public static final String VALID = "valid";
    public static final String EXPRESSION_NOT_VALID = "expression_not_valid";
    public static final String INVALID_IDENTIFIERS_IN_EXPRESSION = "invalid_identifiers_in_expression";
    public static final String FILTER_NOT_EVALUATING_TO_TRUE_OR_FALSE = "filter_not_evaluating_to_true_or_false";

    public static final String UNKNOWN_VARIABLE = "unknown_variable";
    
    private static final Map<String, String> VARIABLE_COLUMNNAME_MAP = ImmutableMap.<String, String>builder().
        put( ProgramIndicator.VAR_EXECUTION_DATE, "executiondate" ).
        put( ProgramIndicator.VAR_DUE_DATE, "duedate" ).
        put( ProgramIndicator.VAR_ENROLLMENT_DATE, "enrollmentdate" ).
        put( ProgramIndicator.VAR_INCIDENT_DATE, "incidentdate" ).
        put( ProgramIndicator.VAR_EVENT_COUNT, "psi" ).
        put( ProgramIndicator.VAR_ENROLLMENT_COUNT, "pi" ).
        put( ProgramIndicator.VAR_TEI_COUNT, "tei" ).build();

    private Program program;

    private String expression;

    private String filter;

    /**
     * Number of decimals to use for indicator value, null implies default.
     */
    private Integer decimals;

    private Boolean displayInForm;

    private Set<ProgramIndicatorGroup> groups = new HashSet<>();

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public ProgramIndicator()
    {
    }

    // -------------------------------------------------------------------------
    // Logic
    // -------------------------------------------------------------------------

    public boolean hasFilter()
    {
        return filter != null;
    }

    public boolean hasDecimals()
    {
        return decimals != null && decimals >= 0;
    }

    /**
     * Returns aggregation type, if not exists returns AVERAGE.
     */
    public AggregationType getAggregationTypeFallback()
    {
        return aggregationType != null ? aggregationType : AggregationType.AVERAGE;
    }

    /**
     * Returns a set of data element and attribute identifiers part of the given
     * input expression.
     *
     * @param input the expression.
     * @return a set of UIDs.
     */
    public static Set<String> getDataElementAndAttributeIdentifiers( String input )
    {
        return Sets.union(
            RegexUtils.getMatches( DATAELEMENT_PATTERN, input, 2 ),
            RegexUtils.getMatches( ATTRIBUTE_PATTERN, input, 1 ) );
    }
    
    /**
     * Returns a set of all analytics columns required for the variables used in the given expression
     *
     * @param expression the program indicator expression.
     * @return a set of column names
     */
    public static Set<String> getVariableColumnNames( String expression )
    {
        Set<String> requiredColumns = new HashSet<String>();
        
        Set<String> variables =
            RegexUtils.getMatches( VARIABLE_PATTERN, expression, 1 );
        
        for ( String variable : variables )
        {
            String columnName = getVariableColumnName( variable );
            if ( null != columnName )
            {
                requiredColumns.add( columnName );
            }
        }
       
        return requiredColumns;
    }
    
    /**
     * Returns the analytics column name associated with the program indicator variable.
     * 
     * @param var the program indicator variable name
     * @return the analytics column name, or null if there is no specific column used for the variable
     */
    public static String getVariableColumnName( String var ) 
    {
        return VARIABLE_COLUMNNAME_MAP.containsKey( var ) ? VARIABLE_COLUMNNAME_MAP.get( var ) : null;
    }

    public void addProgramIndicatorGroup( ProgramIndicatorGroup group )
    {
        groups.add( group );
        group.getMembers().add( this );
    }

    public void removeIndicatorGroup( ProgramIndicatorGroup group )
    {
        groups.remove( group );
        group.getMembers().remove( this );
    }

    public void updateIndicatorGroups( Set<ProgramIndicatorGroup> updates )
    {
        for ( ProgramIndicatorGroup group : new HashSet<>( groups ) )
        {
            if ( !updates.contains( group ) )
            {
                removeIndicatorGroup( group );
            }
        }

        for ( ProgramIndicatorGroup group : updates )
        {
            addProgramIndicatorGroup( group );
        }
    }

    // -------------------------------------------------------------------------
    // DimensionalItemObject
    // -------------------------------------------------------------------------

    @Override
    public DimensionItemType getDimensionItemType()
    {
        return DimensionItemType.PROGRAM_INDICATOR;
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    @JsonProperty
    @JsonSerialize( as = BaseIdentifiableObject.class )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Program getProgram()
    {
        return program;
    }

    public void setProgram( Program program )
    {
        this.program = program;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getExpression()
    {
        return expression;
    }

    public void setExpression( String expression )
    {
        this.expression = expression;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getFilter()
    {
        return filter; // Note: Also overrides DimensionalObject
    }

    public void setFilter( String filter )
    {
        this.filter = filter;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Integer getDecimals()
    {
        return decimals;
    }

    public void setDecimals( Integer decimals )
    {
        this.decimals = decimals;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Boolean getDisplayInForm()
    {
        return displayInForm;
    }

    public void setDisplayInForm( Boolean displayInForm )
    {
        this.displayInForm = displayInForm;
    }


    @JsonProperty( "programIndicatorGroups" )
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JacksonXmlElementWrapper( localName = "programIndicatorGroups", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "programIndicatorGroups", namespace = DxfNamespaces.DXF_2_0 )
    public Set<ProgramIndicatorGroup> getGroups()
    {
        return groups;
    }

    public void setGroups( Set<ProgramIndicatorGroup> groups )
    {
        this.groups = groups;
    }

    @Override
    public void mergeWith( IdentifiableObject other, MergeMode mergeMode )
    {
        super.mergeWith( other, mergeMode );

        if ( other.getClass().isInstance( this ) )
        {
            ProgramIndicator programIndicator = (ProgramIndicator) other;

            if ( mergeMode.isReplace() )
            {
                program = programIndicator.getProgram();
                expression = programIndicator.getExpression();
                filter = programIndicator.getFilter();
                decimals = programIndicator.getDecimals();
                displayInForm = programIndicator.getDisplayInForm();
            }
            else if ( mergeMode.isMerge() )
            {
                program = programIndicator.getProgram() == null ? program : programIndicator.getProgram();
                expression = programIndicator.getExpression() == null ? expression : programIndicator.getExpression();
                filter = programIndicator.getFilter() == null ? filter : programIndicator.getFilter();
                decimals = programIndicator.getDecimals() == null ? decimals : programIndicator.getDecimals();
                displayInForm = programIndicator.getDisplayInForm() == null ? displayInForm : programIndicator.getDisplayInForm();
            }
        }
    }
}
