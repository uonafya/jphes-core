jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'addNationalUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("nationalUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateNationalUnit.action" );

  sortList( 'availableProgramList', 'ASC' );
} );
