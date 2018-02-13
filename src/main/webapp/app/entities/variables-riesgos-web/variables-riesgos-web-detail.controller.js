(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Variables_riesgos_webDetailController', Variables_riesgos_webDetailController);

    Variables_riesgos_webDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Variables_riesgos_web'];

    function Variables_riesgos_webDetailController($scope, $rootScope, $stateParams, previousState, entity, Variables_riesgos_web) {
        var vm = this;

        vm.variables_riesgos_web = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('playasApp:variables_riesgos_webUpdate', function(event, result) {
            vm.variables_riesgos_web = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
