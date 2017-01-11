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
  checkValueIsExist( "code", "validateNationalUnit.action" );
  checkValueIsExist( "shortName", "validateNationalUnit.action" );

  sortList( 'availableProgramList', 'ASC' );
} );
