jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'updateMechanismUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("mechanismUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateMechanismUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "shortName", "validateMechanismUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "code", "validateMechanismUnit.action", {
    id : getFieldValue( 'id' )
  } );

  sortList( 'availableProgramList', 'ASC' );
} );