jQuery( document ).ready( function()
{
  jQuery( "#name" ).focus();

  validation2( 'addDonorUnitForm', function( form )
  {
    selectAllById( 'selectedProgramList' );
    form.submit();
  }, {
    'rules' : getValidationRules("donorUnit")
  } );

  /* remote validation */
  checkValueIsExist( "name", "validateDonorUnit.action" );
  checkValueIsExist( "code", "validateDonorUnit.action" );
  checkValueIsExist( "shortName", "validateDonorUnit.action" );

  sortList( 'availableProgramList', 'ASC' );
} );
