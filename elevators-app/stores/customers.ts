import { create } from 'zustand';

const API_URL = 'http://localhost:8080/api/v1';

interface Floor {
  floorNumber: number;
  people: Person[];
}

interface Person {
  name: string;
  direction: string;
  desiredFloorNumber: number;
}

interface Elevator {
  currentFloor: number;
  serialNumber: number;
  destinationFloor: number;
  state: string;
  people: Person[];
  isOpened: boolean;
}

interface SimulationState {
  buildingId: number;
  floors: Floor[];
  elevators: Elevator[];

  InitSimulation: (numOfFloors: number, numOfElevators: number) => Promise<void>;
  StopSimulation: () => Promise<void>;
  MakeSimulationStep: () => Promise<void>;
  AddPerson: (floorNumber: number, desiredFloorNumber: number, name: string) => Promise<void>;
}

export const useSimulationStore = create<SimulationState>((set) => ({
  buildingId: 0,
  floors: [],
  elevators: [],

  InitSimulation: async (numOfFloors: number, numOfElevators: number) => {
    const response = await fetch(`${API_URL}/simulations`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        numOfFloors,
        numOfElevators,
      }),
    });

    const { buildingId, floors, elevators } = await response.json();

    set({ buildingId: buildingId, floors: floors, elevators: elevators });
  },

  StopSimulation: async () => {
    const { buildingId } = useSimulationStore.getState();
    const response = await fetch(`${API_URL}/simulations/${buildingId}/stop`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const info = await response.text();
    console.log(info);

    set({ buildingId: 0, floors: [], elevators: [] });
  },

  MakeSimulationStep: async () => {
    const { buildingId } = useSimulationStore.getState();
    const response = await fetch(`${API_URL}/simulations/${buildingId}/step`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const { floors, elevators } = await response.json();

    set({ floors: floors, elevators: elevators });
  },

  AddPerson: async (floorNumber: number, desiredFloorNumber: number, name: string) => {
    const { buildingId } = useSimulationStore.getState();
    const response = await fetch(`${API_URL}/buildings/${buildingId}/floors/${floorNumber}/people`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        desiredFloorNumber,
        name,
      }),
    });

    const person: Person = await response.json();
    console.log(person);

    const response_2 = await fetch(`${API_URL}/simulations/${buildingId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const { floors, elevators } = await response_2.json();

    set({ floors: floors, elevators: elevators });
  },
}));
