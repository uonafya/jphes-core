jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'updateDonorUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("donorUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateDonorUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "shortName", "validateDonorUnit.action", {
    id : getFieldValue( 'id' )
  } );

  checkValueIsExist( "code", "validateDonorUnit.action", {
    id : getFieldValue( 'id' )
  } );

  sortList( 'availableProgramList', 'ASC' );
} );