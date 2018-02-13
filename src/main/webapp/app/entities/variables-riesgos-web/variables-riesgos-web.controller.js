(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Variables_riesgos_webController', Variables_riesgos_webController);

    Variables_riesgos_webController.$inject = ['Variables_riesgos_web'];

    function Variables_riesgos_webController(Variables_riesgos_web) {

        var vm = this;

        vm.variables_riesgos_webs = [];

        loadAll();

        function loadAll() {
            Variables_riesgos_web.query(function(result) {
                vm.variables_riesgos_webs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
