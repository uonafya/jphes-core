jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'addMechanismUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("mechanismUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateMechanismUnit.action" );
  checkValueIsExist( "code", "validateMechanismUnit.action" );
  checkValueIsExist( "shortName", "validateMechanismUnit.action" );

  sortList( 'availableProgramList', 'ASC' );
} );
