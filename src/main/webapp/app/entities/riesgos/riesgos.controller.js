(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('RiesgosController', RiesgosController);

    RiesgosController.$inject = ['Riesgos'];

    function RiesgosController(Riesgos) {

        var vm = this;

        vm.riesgos = [];

        loadAll();

        function loadAll() {
            Riesgos.query(function(result) {
                vm.riesgos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
