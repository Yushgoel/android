input_variable = input("Do you want to be assessed for a fracture or a heart attack?")
sum_of_scores = 0

def change_score_factor(word, score):
		word.lower()
		if word == "yes":
			score += 2
		else:
			score += 0

def change_score_symptom(word1, score1):
		if word1 == "yes" or word1 == "Yes":
			score1 += 1
		else:
			score1 += 0

def change_score_2points(word2, score2):
		if word2 == "yes":
			sum_of_scores +=2
		else:
			sum_of_scores += 0

def change_score_1point(word3, score3):
		if word3 == "yes":
			sum_of_scores += 1
		else: 
			sum_of_scores += 0



if input_variable == "heart attack":
	age = input("Are you above 40 of age?")
	gender = input("Are you male?")
	bmi = input("Is your BMI above 30?")
	past_history= input("Do you have any past history of heart attacks?")
	family_history = input("Have any of your close family members (aunts, uncles, parents) have any history of heart attacks?")
	bloodpressure = input("Have you been diagnosed with hypertension (high blood pressure) ?")
	diabetes = input("Have you been diagnosed with diabetes?")
	smoking = input("Are you a regular smoker?")
	alcohol = input("Does your weekly alcohol consumption exceed 200 units (2 liters) ")
	heavy_meal = input("Did you have a heavy meal yesterday?")
	tightness_of_chest = input("Are you feeling any tightness or pressure on your chest?")
	vomiting_nausea = input("Are you feeling any nausesa or vomiting at the moment?")
	sweating = input("Are you sweating heavily?")
	increasepain = input("Is your pain steadily increasing?")
	shortnessbreath = input("Are you feeling any shortness of breath?")

	
	change_score_factor(age, sum_of_scores)
	change_score_factor(gender, sum_of_scores)
	change_score_factor(bmi, sum_of_scores)
	change_score_factor(past_history, sum_of_scores)
	change_score_factor(family_history, sum_of_scores)
	change_score_factor(bloodpressure, sum_of_scores)
	change_score_factor(diabetes, sum_of_scores)
	change_score_factor(smoking, sum_of_scores)
	change_score_factor(alcohol, sum_of_scores)
	
	change_score_symptom(tightness_of_chest, sum_of_scores)
	change_score_symptom(vomiting_nausea, sum_of_scores)
	change_score_symptom(sweating, sum_of_scores)
	change_score_symptom(increasepain, sum_of_scores)
	change_score_symptom(shortnessbreath, sum_of_scores)

	if heavy_meal == "yes" or heavy_meal == "Yes":
		sum_of_scores += 0
	elif heavy_meal == "no" or heavy_meal == "No":
		sum_of_scores += 1

	if sum_of_scores < 5:
    	print(""" You are unlikely to have had a heart attack.
     Instead, you are more probable to be having a case of heart burn, or acid reflux,
     which is not fatal. However, you must take precaution by visiting the doctor quickly if your age is
     greater than 40.""")

    elif sum_of_scores > 5 < 10:
    	print(""" You have a medium probability of having a heart attack at the moment.
     If you are above 40, you have a BMI above 30 and you feel an increasing pain, rush immediately to the hospital 
     to be checked for a heart attack. Otherwise, you are yet again at a larger probability of having a heart burn
     which is not fatal.""")

	else:
    	print(""" You are at a high probability of having a heart attack at the moment. Please rush to the nearest emergency
  	  hospital as soon as possible to get yourself checked.""")

elif input_variable == "fracture" or input_variable == "Fracture":
	stiffness = input("Are you feeling any stiffness in the area of pain?")
	muscularspasm = input("Are you haing muscular spasms (involuntary movements of muscles) in the area of pain?")
	severepain = input("Are you feeling severe pain at the moment?")
	inability_put_weight = input("Are you unable to put any weight on your injured area?")
	protruded_bones = input("Are any of your bones protruding?")
	inability_move_area = input("Were you unable to move the area of pain immediately after the fall?")
	cramping = input("Is the area of pain cramping?")

	change_score_2points(muscularspasm, sum_of_scores)
	change_score_2points(inability_put_weight, sum_of_scores)
	change_score_2points(protruded_bones, sum_of_scores)

	change_score_1point(inability_move_area, sum_of_scores)
	change_score_1point(severepain, sum_of_scores)
	change_score_1point(cramping, sum_of_scores)
	change_score_1point(stiffness, sum_of_scores)

	if sum_of_scores < 4:
		print("You are more probable to have a sprain. The recommended treatment for a sprain includes rest, ice, compression, elevation and medication. If the pain continues and increases in pain, immediately see the doctor for an official diagnosis.")
	elif sum_of_scores > 4 < 7:
		print("You have a medium probability to either possess a sprain or a fracture. Nevertheless, immediately apply ice and compress, and see the doctor at the nearest possible time.")
	else:
		print("You have a high probability of having a fracture. You must immediately go to the doctor for an XRay to assess the situation.")