jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateNationalUnitForm( context ) {
  location.href = 'showUpdateNationalUnitForm.action?id=' + context.id;
}

function showAddDonorUnitForm( context ) {
  location.href = 'showAddDonorUnitForm.action?id=' + context.id;
}

