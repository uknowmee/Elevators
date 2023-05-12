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

    set({ buildingId, floors, elevators });
  },
  StopSimulation: async () => {
  },
  MakeSimulationStep: async () => {
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

    const response_2 = await fetch(`${API_URL}/simulations/${buildingId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const { floors, elevators } = await response_2.json();

    set({ floors, elevators });
  },
}));
