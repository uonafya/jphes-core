jQuery(document).ready(function() {
  tableSorter('listTable');

  dhis2.contextmenu.makeContextMenu({
    menuId: 'contextMenu',
    menuItemActiveClass: 'contextMenuItemActive'
  });
});

function showProgramDetails( context ) {
  location.href = '';
}
