(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Situaciones_riesgosController', Situaciones_riesgosController);

    Situaciones_riesgosController.$inject = ['Situaciones_riesgos'];

    function Situaciones_riesgosController(Situaciones_riesgos) {

        var vm = this;

        vm.situaciones_riesgos = [];

        loadAll();

        function loadAll() {
            Situaciones_riesgos.query(function(result) {
                vm.situaciones_riesgos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
