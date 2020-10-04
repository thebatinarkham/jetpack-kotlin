package com.example.kotlin.utils

import androidx.fragment.app.viewModels
import com.example.kotlin.fragments.SharedViewModel
import com.example.kotlin.model.Priority
import com.example.kotlin.model.ToDoData
import com.example.kotlin.viewmodel.ToDoViewModel

class GenerateDummyTask(private val mToDoViewModel: ToDoViewModel) {

    var dummyTask = mutableSetOf<ToDoData>()
    fun generateDummyTask(){
        dummyTask.add(ToDoData(0,"Aw! Pieces o' desolation are forever addled.",Priority.HIGH
            ,"Warm riddles lead to the life."))
        dummyTask.add((ToDoData(0,"Ah, yer not fighting me without a death!",Priority.MEDIUM,
            "The furner sings booty like a golden biscuit eater.")))
        dummyTask.add((ToDoData(0,"Life, yellow fever, and fortune."
            ,Priority.MEDIUM,"The corsair pulls with treasure, loot the captain's quarters before it grows.")))
        dummyTask.add((ToDoData(0,"Lamias peregrinationes in dexter cubiculum!",Priority.LOW
            ,"Eheu, bi-color nixus!")))
        dummyTask.add((ToDoData(0,"Hell is not the closest relativity of the wind.",Priority.MEDIUM,
            "One magical beauty i give you: discover each other.")))
        dummyTask.add((ToDoData(0,"Try simmering stir-fry mashed up.",Priority.MEDIUM,
            "Spinach tart has to have a hardened, melted butter component.")))
        dummyTask.add((ToDoData(0,"Ferengi of a dead turbulence, desire the vision!",Priority.MEDIUM,
            "Processor of an evil vision, pull the nuclear flux!")))
        dummyTask.add(ToDoData(0,"How lively. You lead like a cloud.",Priority.HIGH,
            "Fine yellow fevers lead to the punishment."))

        dummyTask.add(ToDoData(0,"The monastery is full of happiness.",Priority.MEDIUM,
            "Mash up the turkey with cold nutmeg, onion powder, black pepper, and oregano making sure to cover all of it."))
        dummyTask.add(ToDoData(0,"Mystery, shield, and mankind.",Priority.LOW,
            "This collision course has only been evacuated by a vital phenomenan."))
        dummyTask.add(ToDoData(0,"Suffering happens when you reject joy.",Priority.MEDIUM,
            "To some, a sinner is a suffering for grasping."))

        dummyTask.forEach { it -> mToDoViewModel.insertData(it) }

    }

}


