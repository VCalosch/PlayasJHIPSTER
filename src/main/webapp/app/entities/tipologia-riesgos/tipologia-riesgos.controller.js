(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Tipologia_riesgosController', Tipologia_riesgosController);

    Tipologia_riesgosController.$inject = ['Tipologia_riesgos'];

    function Tipologia_riesgosController(Tipologia_riesgos) {

        var vm = this;

        vm.tipologia_riesgos = [];

        loadAll();

        function loadAll() {
            Tipologia_riesgos.query(function(result) {
                vm.tipologia_riesgos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
