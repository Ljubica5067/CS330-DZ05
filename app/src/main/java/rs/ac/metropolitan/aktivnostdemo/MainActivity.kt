package rs.ac.metropolitan.aktivnostdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var s = "Životni ciklus aktivnosti"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(s, "Pokrećem onCreate() događaj")
    }

    public override fun onStart() {
        super.onStart()
        Log.d(s, "Pokrećem onStart() događaj")
    }

    public override fun onRestart() {
        super.onRestart()
        Log.d(s, "Pokrećem onRestart() događaj")
    }

    public override fun onResume() {
        super.onResume()
        Log.d(s, "Pokrećem onResume() događaj")
    }

    public override fun onPause() {
        super.onPause()
        Log.d(s, "Pokrećem onPause() događaj")
    }

    public override fun onStop() {
        super.onStop()
        Log.d(s, "Pokrećem onStop() događaj")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d(s, "Pokrećem onDestroy() događaj")
    }
}