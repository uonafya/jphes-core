jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showUpdateProgramForm( context ) {
  location.href = 'showUpdateProgramForm.action?id=' + context.id;
}

