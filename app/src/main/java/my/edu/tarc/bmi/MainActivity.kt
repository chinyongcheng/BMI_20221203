package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.bmi.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    //module level variable
    //lateinit = late initialize
    //val = value vs var = variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate = display the UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        //root = top of a tree structure -> layout
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        //local variable

        binding.buttonReset.setOnClickListener{
            binding.textViewBMI.text = String.format("%s", getString(R.string.app_name))
            binding.imageViewBMI.setImageResource(R.drawable.empty)
            binding.editTextNumberDecimalWeight.text.clear()
            binding.editTextNumberDecimalHeight.text.clear()
        }

        binding.buttonCalculate.setOnClickListener{

            if(binding.editTextNumberDecimalHeight.text.isEmpty()){
                binding.editTextNumberDecimalHeight.setError(
                    getString(R.string.value_required))
                return@setOnClickListener
            }

            if(binding.editTextNumberDecimalWeight.text.isEmpty()){
                binding.editTextNumberDecimalWeight.setError(
                    getString(R.string.value_required))
                return@setOnClickListener
            }

            val weight = binding.editTextNumberDecimalWeight.text.toString().toFloat()
            val height = binding.editTextNumberDecimalHeight.text.toString().toFloat()
            val bmi = weight/(height/100).pow(2)

            if(bmi < 18.5){
                //underweight
                //BMI: 18.20 (Underweight)
                binding.textViewBMI.text = String.format("%s: %.2f (%s)", getString(R.string.app_name), bmi, getString(R.string.underweight))
                binding.imageViewBMI.setImageResource(R.drawable.under)
            }else if(bmi > 24.9){
                //overweight
                binding.textViewBMI.text = String.format("%s: %.2f (%s)", getString(R.string.app_name), bmi, getString(R.string.overweitght))
                binding.imageViewBMI.setImageResource(R.drawable.over)
            }else{
                //normal
                binding.textViewBMI.text = String.format("%s: %.2f (%s)", getString(R.string.app_name), bmi, getString(R.string.normal))
                binding.imageViewBMI.setImageResource(R.drawable.normal)
            }

        }
    }
}