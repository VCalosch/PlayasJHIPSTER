'use strict';

describe('Controller Tests', function() {

    describe('Tipologia_riesgos Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockTipologia_riesgos, MockRiesgos;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockTipologia_riesgos = jasmine.createSpy('MockTipologia_riesgos');
            MockRiesgos = jasmine.createSpy('MockRiesgos');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Tipologia_riesgos': MockTipologia_riesgos,
                'Riesgos': MockRiesgos
            };
            createController = function() {
                $injector.get('$controller')("Tipologia_riesgosDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'playasApp:tipologia_riesgosUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
