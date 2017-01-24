jQuery( document ).ready( function()
{
    jQuery( "#name" ).focus();

    validation2( 'updateProgramForm', function( form )
    {
        selectAllById( 'deSelectedList' );
        selectAllById( 'indSelectedList' );
        form.submit();
    }, {
        'rules' : getValidationRules("jphesProgram")
    } );

    /* remote validation */
    checkValueIsExist( "name", "validateProgram.action", {
        id : getFieldValue( 'id' )
    } );

    checkValueIsExist( "code", "validateProgram.action", {
        id : getFieldValue( 'id' )
    } );

    sortList( 'iAvailable', 'ASC' );
    sortList( 'deAvailable', 'ASC' );

} );
