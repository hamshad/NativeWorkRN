import { NativeModules } from "react-native";
const { LocationWorkManagerModule } = NativeModules;

export function startLocationLogging() {
  LocationWorkManagerModule.startLocationLogging();
}
