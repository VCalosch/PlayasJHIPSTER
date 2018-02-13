(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Variables_riesgos_webDialogController', Variables_riesgos_webDialogController);

    Variables_riesgos_webDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Variables_riesgos_web'];

    function Variables_riesgos_webDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Variables_riesgos_web) {
        var vm = this;

        vm.variables_riesgos_web = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.variables_riesgos_web.id !== null) {
                Variables_riesgos_web.update(vm.variables_riesgos_web, onSaveSuccess, onSaveError);
            } else {
                Variables_riesgos_web.save(vm.variables_riesgos_web, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('playasApp:variables_riesgos_webUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fecha = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
