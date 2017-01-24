jQuery( document ).ready( function()
{
    jQuery( "#name" ).focus();

    validation2( 'addProgramForm', function( form )
    {
        selectAllById( 'indSelected' );
        selectAllById( 'deSelected' );
        form.submit();
    }, {
        'rules' : getValidationRules("jphesProgram")
    } );

    /* remote validation */
    checkValueIsExist( "name", "validateProgram.action" );
    checkValueIsExist( "code", "validateProgram.action" );

    sortList( 'iAvailable', 'ASC' );
    sortList( 'deAvailable', 'ASC' );

} );
