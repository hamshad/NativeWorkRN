import { NativeModules } from "react-native";
const { WorkManagerModule } = NativeModules;

export function startBackgroundWork() {
  WorkManagerModule.startWork();
}
