import React from 'react';
import type { PropsWithChildren } from 'react';
import {
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';
import { startBackgroundWork } from './src/WorkManagerModule';
import { startLocationLogging } from './src/LocationWorkManagerModule';
import { checkMultiple, PERMISSIONS, requestMultiple } from 'react-native-permissions'

function App(): React.JSX.Element {


  React.useEffect(() => {
    const permissions = [PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION, PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION];


    checkMultiple(permissions).then((statuses) => {
      if (statuses[PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION] !== 'granted' || statuses[PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION] !== 'granted') {

        requestMultiple(permissions).then((statuses) => {
          if (statuses[PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION] === 'granted' && statuses[PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION] === 'granted') {
            console.log('Location permissions granted');
          } else {
            console.log('Location permissions denied');
          }
        });
      } else {
        console.log('Location permissions already granted');
      }
    });

  }, [0])


  return (
    <View style={styles.container}>
      <TouchableOpacity style={{ padding: 24 }} onPress={startBackgroundWork}>
        <Text style={{ fontSize: 24, fontFamily: 'bold' }}>Start Work Manager</Text>
      </TouchableOpacity>

      <TouchableOpacity style={{ padding: 24, marginTop: 24 }} onPress={startLocationLogging}>
        <Text style={{ fontSize: 24, fontFamily: 'bold' }}>Start Tracking Location and Logging</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f0f0f0'
  }
});

export default App;
