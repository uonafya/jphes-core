jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateDonorUnitForm( context ) {
  location.href = 'showUpdateDonorUnitForm.action?id=' + context.id;
}

function showAddAgencyUnitForm( context ) {
  location.href = 'showAddAgencyUnitForm.action?id=' + context.id;
}
